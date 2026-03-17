import {
  Component,
  ElementRef,
  input,
  model,
  viewChild,
  effect,
} from '@angular/core';

@Component({
  selector: 'app-modal',
  imports: [],
  templateUrl: './modal.html',
  styleUrl: './modal.css',
  standalone: true,
})
export class ModalComponent {
  title = input<string>('');
  isOpen = model<boolean>(false);

  dialog = viewChild<ElementRef<HTMLDialogElement>>('modal');

  constructor() {
    effect(() => {
      const modalElement = this.dialog()?.nativeElement;
      if (!modalElement) return;

      if (this.isOpen()) {
        modalElement.showModal();
      } else {
        modalElement.close();
      }
    });
  }

  onClose(): void {
    this.isOpen.set(false);
  }
}
