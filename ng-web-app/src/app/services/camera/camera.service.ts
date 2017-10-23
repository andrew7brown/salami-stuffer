import { Injectable } from '@angular/core';

import { Camera } from '../../entities/camera'
import { CAMERAS } from '../../mock-cameras'

import { Observable } from "rxjs";
import { IntervalObservable } from "rxjs/observable/IntervalObservable";

import { HttpClient } from '@angular/common/http';

@Injectable()
export class CameraService {

private timer: Observable<number>;
private interval: number;
private cams: Camera[];
cameras: any;

  constructor(private http: HttpClient) {

  	this.interval = 10000;
    this.timer = Observable.timer(0, this.interval);
     	this.timer
  		.takeWhile(() => true)
  		.subscribe(() => {
          this.fetchCamerasFromServer().then(data => {
            this.cams = data;
            console.log(this.cams);
          })
  		});
  }

  getMockCamerasPromise(): Promise<Camera[]> {
  	return Promise.resolve(CAMERAS);
  }

  getCameras(): Observable<Camera[]> {
  	return Observable.of(this.cams);
  }

  async fetchCamerasFromServer(): Promise<Camera[]> {
    try {
      let response = await this.http.get('http://localhost:8080/cameras/').toPromise();
      return response['_embedded']['cameras'];
    } catch (error) {
      console.log(error);
    }
  }

}
