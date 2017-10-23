import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MdButtonModule, MdCheckboxModule, MatListModule, MatDialogModule, MatCardModule } from '@angular/material';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { MapComponent } from './map/map.component';

import { AgmCoreModule, GoogleMapsAPIWrapper } from '@agm/core';
import { ListComponent } from './list/list.component';

import { CameraService } from './services/camera/camera.service';
import { DetailsModalComponent } from './details-modal/details-modal.component';
import { CoreMapContentComponent } from './map/core-map-content/core-map-content.component';

@NgModule({
  declarations: [
    AppComponent,
    MapComponent,
    ListComponent,
    DetailsModalComponent,
    CoreMapContentComponent
  ],
  imports: [
    AgmCoreModule.forRoot({
    	apiKey: 'AIzaSyBPggtBW5crZkrr6ss90U_FyWnMZVk7lCg'
    }),
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatCardModule,
    MatDialogModule,
    MatListModule,
    MdButtonModule,
    MdCheckboxModule,
  ],
  entryComponents: [
    DetailsModalComponent
  ],
  providers: [CameraService, GoogleMapsAPIWrapper],
  bootstrap: [AppComponent]
})
export class AppModule { }
