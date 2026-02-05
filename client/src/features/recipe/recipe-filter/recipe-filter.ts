import { CommonModule } from '@angular/common';
import {
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  Component,
  inject,
  ViewChild,
  OnInit,
  signal,
} from '@angular/core';
import {
  FormArray,
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
} from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faCarrot, faEarthAmerica, faUtensils } from '@fortawesome/free-solid-svg-icons';
import { debounceTime, distinctUntilChanged, map, Observable, of, take, combineLatest, startWith } from 'rxjs';
import { Country } from 'src/entities/Country/model/country';
import { CountryService } from 'src/entities/Country/service/country.service';
import { Ingredient } from 'src/entities/Ingredient/model/ingredient';
import { IngredientService } from 'src/entities/Ingredient/service/ingredient.service';
import { Category, RecipeFilters } from 'src/entities/Recipe/model/recipe-filters';
import { RecipeService } from 'src/entities/Recipe/service/recipe-service';
import { ButtonFilterComponent } from 'src/shared/components/button-filter/button-filter';
import { ModalComponent } from 'src/shared/components/modal/modal';

type IngredientState = 'NONE' | 'INCLUDED' | 'EXCLUDED';

@Component({
  selector: 'app-recipe-filter',
  standalone: true,
  imports: [
    CommonModule,
    ButtonFilterComponent,
    FontAwesomeModule,
    ModalComponent,
    ReactiveFormsModule,
  ],
  templateUrl: './recipe-filter.html',
  styleUrl: './recipe-filter.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class RecipeFilter implements OnInit {
  @ViewChild(ModalComponent) modal!: ModalComponent;

  // Injections des services et du FormBuilder
  private fb = inject(FormBuilder);
  private countryService = inject(CountryService);
  private recipeService = inject(RecipeService);
  private ingredientService = inject(IngredientService);
  private cd = inject(ChangeDetectorRef);

  // Icones
  carrotIcon: IconDefinition = faCarrot;
  earthIcon: IconDefinition = faEarthAmerica;
  utensilsIcon: IconDefinition = faUtensils;

  modalTitle = '';
  currentFilterType: 'COUNTRY' | 'CATEGORY' | 'TASTE' | null = null;
  // Formulaire réactif pour les filtres
  filterForm: FormGroup = this.fb.group({
    items: this.fb.array([]),
  });

  // *** Pour le filtre sur les ingrédients (Goûts) ***
  // Barre de recherche pour les ingrédients
  searchControl = new FormControl('');
  // Stockage de tous les ingrédients chargés depuis le service
  private allIngredients$: Observable<Ingredient[]> = of([]); // Initialize with an empty observable
  // Signal pour stocker les ingrédients filtrés en fonction de la barre de recherche
  filteredIngredients$: Observable<Ingredient[]> = of([]); // Already initialized
  // Signal pour stocker les ingrédients sélectionnés avec leur état (inclus/exclus)
  selectedIngredients = signal<{ ingredient: Ingredient; state: IngredientState }[]>([]);

  get items(): FormArray {
    return this.filterForm.get('items') as FormArray;
  }

  ngOnInit() {
    this.allIngredients$ = this.ingredientService.getAllIngredients().pipe(take(1)); // Load all ingredients once

    const searchTerm$ = this.searchControl.valueChanges.pipe(
      startWith(''), // Emit empty string on initialization
      debounceTime(300),
      distinctUntilChanged()
    );

    this.filteredIngredients$ = combineLatest([this.allIngredients$, searchTerm$]).pipe(
      map(([allIngredients, searchTerm]) => {
        if (searchTerm && searchTerm.length > 0) {
          const lowerCaseSearchTerm = searchTerm.toLowerCase();
          return allIngredients.filter(ingredient =>
            ingredient.name.toLowerCase().includes(lowerCaseSearchTerm)
          );
        }
        return allIngredients; // Show all ingredients if search term is empty or short
      })
    );
  }

  getIngredientState(ingredient: Ingredient): 'INCLUDED' | 'EXCLUDED' | 'NONE' {
    const selected = this.selectedIngredients().find(
      (item) => item.ingredient.id === ingredient.id,
    );
    return selected ? selected.state : 'NONE';
  }

  toggleIngredient(ingredient: Ingredient) {
    const existingIndex = this.selectedIngredients().findIndex(
      (item) => item.ingredient.id === ingredient.id,
    );

    if (existingIndex > -1) {
      // Ingredient already exists, cycle its state
      const current = this.selectedIngredients()[existingIndex];
      if (current.state === 'INCLUDED') {
        this.selectedIngredients.update((prev) => {
          const updated = [...prev];
          updated[existingIndex].state = 'EXCLUDED';
          return updated;
        });
      } else {
        // Was EXCLUDED, remove it (cycle back to None)
        this.selectedIngredients.update((prev) => {
          return prev.filter((_, index) => index !== existingIndex);
        });
      }
    } else {
      // Ingredient not found, add it as INCLUDED
      this.selectedIngredients.update((prev) => [...prev, { ingredient, state: 'INCLUDED' }]);
    }
    this.cd.detectChanges(); // Manually trigger change detection
  }

  openModal(filterType: 'COUNTRY' | 'CATEGORY' | 'TASTE') {
    this.currentFilterType = filterType;
    this.items.clear();
    if (filterType === 'COUNTRY') {
      this.modalTitle = "Choix du pays d'origine";
      this.countryService
        .getAllCountries()
        .pipe(take(1))
        .subscribe((countries) => {
          this.populateFormArray(countries);
          this.cd.detectChanges();
          this.modal.open();
        });
    } else if (filterType === 'CATEGORY') {
      this.modalTitle = 'Choix de régime alimentaire';
      const categories = Object.keys(Category).map((key) => ({
        key,
        label: Category[key as keyof typeof Category],
      }));
      this.populateFormArray(categories);
      this.cd.detectChanges();
      this.modal.open();
    } else if (filterType === 'TASTE') {
      this.modalTitle = 'Choix des goûts';
      this.searchControl.setValue(''); // Clear search input when opening modal
      // No need to clear selectedIngredients here, as they persist across opening/closing for TASTE
      this.cd.detectChanges();
      this.modal.open();
    }
  }

  private populateFormArray(items: (Country | { key: string; label: string })[]) {
    items.forEach((item) => {
      this.items.push(
        this.fb.group({
          value: item,
          selected: false,
        }),
      );
    });
  }

  applyFilters() {
    const selectedItem = this.filterForm.value.items.find(
      (item: { selected: boolean }) => item.selected,
    );

    if (selectedItem) {
      let filters: RecipeFilters = {};
      if (this.currentFilterType === 'COUNTRY') {
        filters = { countryId: (selectedItem.value as Country).id };
      } else if (this.currentFilterType === 'CATEGORY') {
        filters = { category: selectedItem.value.key as Category };
      } else if (this.currentFilterType === 'TASTE') {
        const includedIngredientIds = this.selectedIngredients()
          .filter((item) => item.state === 'INCLUDED')
          .map((item) => item.ingredient.id);
        const excludedIngredientIds = this.selectedIngredients()
          .filter((item) => item.state === 'EXCLUDED')
          .map((item) => item.ingredient.id);

        filters = { includedIngredientIds, excludedIngredientIds };
      }
      this.recipeService.loadRecipes(filters);
    } else {
      // If no specific filter type is handled or no item is selected, load all recipes
      // For TASTE filter, if selectedIngredients is empty, this will effectively clear taste filters
      if (this.currentFilterType === 'TASTE' && this.selectedIngredients.length > 0) {
        const includedIngredientIds = this.selectedIngredients()
          .filter((item) => item.state === 'INCLUDED')
          .map((item) => item.ingredient.id);
        const excludedIngredientIds = this.selectedIngredients()
          .filter((item) => item.state === 'EXCLUDED')
          .map((item) => item.ingredient.id);
        this.recipeService.loadRecipes({ includedIngredientIds, excludedIngredientIds });
      } else {
        this.recipeService.loadRecipes();
      }
    }
    this.close();
  }

  close() {
    this.modal.onClose();
  }
}
