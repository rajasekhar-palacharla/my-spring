import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { UserComponent } from './user/user.component';
import { AppRoutingModule } from './app.routing.module';
import {UserService} from './user/user.service';
import {HttpClientModule} from "@angular/common/http";
import {AddUserComponent} from './user/add-user.component';
import { ImageUploadComponent} from './user/upload/image-upload.component';
import { ImageDisplayComponent} from './user/image-display/image-display.component';
import  { UploadFileService} from './user/upload-file.service';

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    AddUserComponent,
    ImageUploadComponent,
    ImageDisplayComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [UserService, UploadFileService],
  bootstrap: [AppComponent]
})
export class AppModule { }
