import { CommonModule } from '@angular/common';
import { Component, inject, ViewChild } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { IconDefinition } from '@fortawesome/fontawesome-svg-core';
import { faCarrot, faEarthAmerica, faUtensils } from '@fortawesome/free-solid-svg-icons';
import { take } from 'rxjs';
import { Country } from 'src/entities/Country/model/country';
import { CountryService } from 'src/entities/Country/service/country.service';
import { Category, RecipeFilters } from 'src/entities/Recipe/model/recipe-filters';
import { RecipeService } from 'src/entities/Recipe/service/recipe-service';
import { ButtonFilterComponent } from 'src/shared/components/button-filter/button-filter';
import { ModalComponent } from 'src/shared/components/modal/modal';

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
})
export class RecipeFilter {
  @ViewChild(ModalComponent) modal!: ModalComponent;

  // Injections des services et du FormBuilder
  private fb = inject(FormBuilder);
  private countryService = inject(CountryService);
  private recipeService = inject(RecipeService);

  // Icones
  carrotIcon: IconDefinition = faCarrot;
  earthIcon: IconDefinition = faEarthAmerica;
  utensilsIcon: IconDefinition = faUtensils;

  modalTitle = '';
  currentFilterType: 'COUNTRY' | 'CATEGORY' | null = null;
  // Formulaire réactif pour les filtres
  filterForm: FormGroup = this.fb.group({
    items: this.fb.array([]),
  });

  get items(): FormArray {
    return this.filterForm.get('items') as FormArray;
  }

  openModal(filterType: 'COUNTRY' | 'CATEGORY') {
    this.currentFilterType = filterType;
    this.items.clear();

    if (filterType === 'COUNTRY') {
      this.modalTitle = "Choix du pays d'origine";
      this.countryService
        .getAllCountries()
        .pipe(take(1))
        .subscribe((countries) => {
          this.populateFormArray(countries);
          this.modal.open();
        });
    } else if (filterType === 'CATEGORY') {
      this.modalTitle = 'Choix de régime alimentaire';
      const categories = Object.keys(Category).map((key) => ({
        key,
        label: Category[key as keyof typeof Category],
      }));
      this.populateFormArray(categories);
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
      }
      this.recipeService.loadRecipes(filters);
    } else {
      // Optional: Load all recipes if no filter is selected
      this.recipeService.loadRecipes();
    }
    this.close();
  }

  close() {
    this.modal.onClose();
  }
}
