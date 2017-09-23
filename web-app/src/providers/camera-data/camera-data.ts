import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';

/*
  Generated class for the CameraDataProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class CameraDataProvider {

  cameras: any;

  constructor(public http: Http) {
  	this.fetchCamerasFromServer();
  }

  filterItems(searchTerm){
  	if (searchTerm === '') {
  		return this.cameras;
  	}
    return this.cameras.filter((camera) => {
        return camera.namedLocation.toLowerCase().indexOf(searchTerm.toLowerCase()) > -1;
    });     

   }

   async fetchCamerasFromServer(){
   	  return await this.http.get('http://localhost:8080/cameras/').map(res => res.json()).subscribe(data => {
        this.cameras = data._embedded.cameras;
      });
   }

   getCameras() {
   	return this.cameras;
   }

}
