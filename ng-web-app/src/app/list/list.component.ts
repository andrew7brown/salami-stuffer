import { Component, Input, OnInit, OnDestroy } from '@angular/core';

import { CameraService } from '../camera.service';
import { Camera } from '../camera';

import { DetailsModalComponent } from '../details-modal/details-modal.component'
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';

import { Observable } from "rxjs";
import { IntervalObservable } from "rxjs/observable/IntervalObservable";

import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  @Input('cams') private cams: Camera[];
  private interval: number;
  private timer: Observable<number>;
  private alive: boolean;


  constructor(private cameraService: CameraService, public dialog: MdDialog) {
    this.alive = true;
    this.interval = 10000;
    this.timer = Observable.timer(0, this.interval);
  }

  openDialog(camera: Camera): void {
    let dialogRef = this.dialog.open(DetailsModalComponent, {
      data: { camera:  camera }
    });
  }

  ngOnInit() {
    // this.timer
    //   .takeWhile(() => this.alive)
    //   .subscribe(() => {
    //     this.cameraService.getCameras().subscribe((cameras) => {
    //       this.cams = cameras;
    //       console.log('fetching new cameras list');
    //     });
    //   });
  }

  ngOnDestroy() {
    this.alive = false; // switches your IntervalObservable off
  }

}
