import { Component, OnInit, OnDestroy } from '@angular/core';
import { CameraService } from './camera.service';
import { Camera } from './camera';
import { Observable } from "rxjs";
import { IntervalObservable } from "rxjs/observable/IntervalObservable";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  private cams: Camera[];
  private interval: number;
  private timer: Observable<number>;
  private alive: boolean;

  constructor(private cameraService: CameraService) {
    this.alive = true;
    this.interval = 10000;
    this.timer = Observable.timer(0, this.interval);
  }

  ngOnInit() {
    this.timer
      .takeWhile(() => this.alive)
        .subscribe(() => {
          this.cameraService.getCameras().subscribe((cameras) => {
            this.cams = cameras;
            console.log('fetching new cameras list');
          });
      });
  }

  title = 'app';
}
