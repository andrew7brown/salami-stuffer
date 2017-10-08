import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { RetrievedImg } from './retrieved-img/retrieved-img'

/**
 * Generated class for the DetailsPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@Component({
  selector: 'page-details',
  templateUrl: 'details.html',
})
export class DetailsPage {

  name: string;
  latitude: string;
  longitude: string;
  url: string;
  active: boolean;
  photos: any;
  results: any;
  img: any;

  constructor(public navCtrl: NavController, public http: Http, public navParams: NavParams) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad DetailsPage');
    this.name = this.navParams.get('namedLocation');
    this.latitude = this.navParams.get('latitude');
    this.longitude = this.navParams.get('longitude');
    this.url = this.navParams.get('url');
    this.active = this.navParams.get('active');
    this.photos = this.navParams.get('photos');
    this.results = this.navParams.get('photos.results')
    console.log(this.photos);


  }

  getImage(name: String) {

  return Observable.create(observer=>{
  let req = new XMLHttpRequest();
  req.open('get','http://localhost:8080/image?url=' + name);
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

  setUrl(index: number){
    console.log('index: ' + index)
  }
}
