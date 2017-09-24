import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

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

  constructor(public navCtrl: NavController, public navParams: NavParams) {
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

}
