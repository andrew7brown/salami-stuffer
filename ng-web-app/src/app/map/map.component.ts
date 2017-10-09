import { Component, Input, OnInit, OnDestroy } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from "rxjs";
import { IntervalObservable } from "rxjs/observable/IntervalObservable";
import 'rxjs/add/operator/map';

import { Camera } from '../camera';

import { DetailsModalComponent } from '../details-modal/details-modal.component'
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';

import { GoogleMapsAPIWrapper } from '@agm/core';
declare var google: any;

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit, OnDestroy{

map: any;
@Input('cams')private cams: Camera[];
private lat: number;
private lng: number;
private zoom: number;

  constructor(public gMaps: GoogleMapsAPIWrapper, public dialog: MdDialog) {
  	this.lat = 39.7;
  	this.lng = -104;
  	this.zoom = 1;
  }

  centerMap(camera: Camera) {
    console.log("centering " + camera.latitude + ' ' + camera.longitude)
    const position = new google.maps.LatLng(camera.latitude, camera.longitude);
    this.map.panTo(position);
    this.openDialog(camera)
  }

  public loadAPIWrapper(map) {
    this.map = map;
  }

  openDialog(camera: Camera): void {
    let dialogRef = this.dialog.open(DetailsModalComponent, {
      data: { camera:  camera }
    });
  }

  ngOnInit() {

  }

  ngOnDestroy() {
  }

}
