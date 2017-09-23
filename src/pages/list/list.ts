import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';
import { CameraDataProvider } from '../../providers/camera-data/camera-data';
import { DetailsPage } from '../details/details';

@Component({
  selector: 'page-list',
  templateUrl: 'list.html'
})
export class ListPage {	

  cameras: any;
  searchTerm: string = '';

  launchDetailsPage(camera){
  	console.log("sending " + camera)
    this.navCtrl.push(DetailsPage, camera);
  }

  constructor(public navCtrl: NavController, public http: Http, public dataService: CameraDataProvider) {
  	this.http.get('http://localhost:8080/cameras/').map(res => res.json()).subscribe(data => {
    this.cameras = data._embedded.cameras;
	});
  }

  onViewDidLoad() {
 	this.dataService.fetchCamerasFromServer().then(() => this.cameras = this.dataService.getCameras());
  }
 
  setFilteredItems() {
    this.cameras = this.dataService.filterItems(this.searchTerm);
  }

}
