import { Component, Inject } from '@angular/core';

import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-details-modal',
  templateUrl: './details-modal.component.html',
  styleUrls: ['./details-modal.component.css']
})
export class DetailsModalComponent {

  constructor(public dialogRef: MdDialogRef<DetailsModalComponent>,
    @Inject(MD_DIALOG_DATA) public data: any) { }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
