import { Component, OnInit, Input } from '@angular/core';
import { CameraService } from '../services/camera/camera.service';

@Component({
  selector: 'app-async-image',
  template: `<img [src]="img | safe" />`,
  styleUrls: ['./async-image.component.css']
})
export class AsyncImageComponent implements OnInit {
  
  @Input() uuid: string;
  public img: any = 'http://via.placeholder.com/350x150';

  constructor(private cameraService: CameraService) { }

  public async ngOnInit(): Promise<void> {
       this.img = await this.cameraService.getImage(this.uuid);
  }

}
