import { Component, Input } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { DomSanitizer } from '@angular/platform-browser';

/**
 * Generated class for the DetailsPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@Component({
  selector: 'retrieved-img',
  template: `<img *ngIf="tmpUrl" [src]="tmpUrl" />`
})
export class RetrievedImg {
  
  @Input() imageName: string;
  tmpUrl: string;
  url: string;

  constructor(public navCtrl: NavController, public http: Http, public navParams: NavParams, private sanitizer:DomSanitizer) {

    // this.getImage(name).subscribe(imageData =>{
    //   this.tmpUrl = URL.createObjectURL(new Blob([imageData]));
    // });

    this.url = 'http://localhost:8080/image?url=susandennis.2017-09-23.jpg';

    this.getImage(this.transform(this.url)).subscribe(imageData =>{
      this.tmpUrl = URL.createObjectURL(new Blob([imageData]));
    });

    this.http.get(this.url).subscribe(image=>{
      console.log('the url: ' + this.url);
      console.log(image.arrayBuffer());
    })
  }

  ionViewDidLoad() {


  }

  transform(html) {
    return this.sanitizer.bypassSecurityTrustHtml(html);
  }

  getImage(url :any){ 
    console.log('getting img');
    return Observable.create(observer=>{
      let req = new XMLHttpRequest();
      req.open('get', url);
      req.responseType = "arraybuffer";
      req.onreadystatechange = function() {
        if (req.readyState == 4 && req.status == 200) {
          observer.next(req.response);
          observer.complete();
        }
      };
      req.send();
    });
  }

}
