import { Component, ElementRef, EventEmitter, Input, Output, ViewChild } from '@angular/core';

@Component({
  selector: 'app-modal',
  imports: [],
  templateUrl: './modal.html',
  styleUrl: './modal.css',
  standalone: true,
})
export class ModalComponent {
  @Input() title = '';
  @Output() closeModal = new EventEmitter<void>();

  @ViewChild('modal') private dialog!: ElementRef<HTMLDialogElement>;

  open(): void {
    this.dialog.nativeElement.showModal();
  }

  onClose(): void {
    this.dialog.nativeElement.close();
    this.closeModal.emit();
  }
}
