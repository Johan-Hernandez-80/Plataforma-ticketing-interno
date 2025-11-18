import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardComponent } from '../../atoms/card/card.component';

@Component({
    selector: 'app-file-upload-card',
    standalone: true,
    imports: [CommonModule, CardComponent],
    templateUrl: './file-upload-card.component.html',
    styleUrl: './file-upload-card.component.css'
})
export class FileUploadCardComponent {
    @Input() placeholder: string = 'Prueba (png, pdf) máximo 50mb';
    @Input() accept: string = '.png,.pdf';
    @Input() maxSizeMb: number = 50;
    @Input() width: string = '100%';
    @Input() height: string = '90px';
    @Output() fileSelected = new EventEmitter<File>();
    @Output() fileRejected = new EventEmitter<string>();

    fileName: string = '';
    dragging = false;

    onBrowse(input: HTMLInputElement) {
        input.click();
    }

    onFileChange(event: Event) {
        const input = event.target as HTMLInputElement;
        if (!input.files || input.files.length === 0) return;
        const file = input.files[0];

        if (file.size > this.maxSizeMb * 1024 * 1024) {
            this.fileRejected.emit('El archivo excede el tamaño máximo');
            return;
        }

        const allowed = this.accept.split(',').map(ext => ext.trim().toLowerCase());
        const fileExt = '.' + file.name.split('.').pop()!.toLowerCase();
        if (!allowed.includes(fileExt)) {
            this.fileRejected.emit('Tipo de archivo no permitido');
            return;
        }

        this.fileName = file.name;
        this.fileSelected.emit(file);
    }

    onDragOver(e: DragEvent) {
        e.preventDefault();
        this.dragging = true;
    }

    onDragLeave(e: DragEvent) {
        e.preventDefault();
        this.dragging = false;
    }

    onDrop(e: DragEvent) {
        e.preventDefault();
        this.dragging = false;
        if (!e.dataTransfer || e.dataTransfer.files.length === 0) return;
        const file = e.dataTransfer.files[0];

        if (file.size > this.maxSizeMb * 1024 * 1024) {
            this.fileRejected.emit('El archivo excede el tamaño máximo');
            return;
        }
        const allowed = this.accept.split(',').map(ext => ext.trim().toLowerCase());
        const fileExt = '.' + file.name.split('.').pop()!.toLowerCase();
        if (!allowed.includes(fileExt)) {
            this.fileRejected.emit('Tipo de archivo no permitido');
            return;
        }
        this.fileName = file.name;
        this.fileSelected.emit(file);
    }
}
