import { Component, Input, OnInit, OnDestroy } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from "rxjs";
import { IntervalObservable } from "rxjs/observable/IntervalObservable";
import 'rxjs/add/operator/map';

import { CameraService } from '../camera.service';
import { Camera } from '../camera';

import { GoogleMapsAPIWrapper } from '@agm/core';
declare var google: any;

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit, OnDestroy{

map: any;
private cams: Camera[];
private lat: number;
private lng: number;
private zoom: number;
private alive: boolean;
private timer: Observable<number>;
private interval: number;

  constructor(private cameraService: CameraService, public gMaps: GoogleMapsAPIWrapper) {

  	this.lat = 39.7;
  	this.lng = -104;
  	this.zoom = 1;
  	this.alive = true;
  	this.interval = 10000;
    this.timer = Observable.timer(0, this.interval);
  }

  getMockCameras() {
  	return this.cameraService.getMockCamerasPromise().then(cameras => this.cams = cameras);
  }

  centerMap(latitude: number, longitude: number) {
    console.log("centering " + latitude + ' ' + longitude)
    const position = new google.maps.LatLng(latitude, longitude);
    this.map.panTo(position);
  }

  public loadAPIWrapper(map) {
    this.map = map;
  }

  ngOnInit() {
  	this.timer
  		.takeWhile(() => this.alive)
  		.subscribe(() => {
  			this.cameraService.getCameras().subscribe((cameras) => {
  				this.cams = cameras;
  				console.log('fetching new cameras');
  			});
  		});
  }

  ngOnDestroy() {
    this.alive = false; // switches your IntervalObservable off
  }

}
