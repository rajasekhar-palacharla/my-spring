import {Component, OnInit, Input} from '@angular/core';
import { ImageUploadComponent } from '../upload/image-upload.component';
import { UploadFileService } from '../upload-file.service';
import 'rxjs/add/operator/catch';

@Component({
  selector: 'image-display',
  templateUrl: './image-display.component.html',
  styleUrls: ['./image-display.component.css']
})
export class ImageDisplayComponent implements OnInit {

  @Input() 
  uid: number;
  showFileUpload: boolean = true;
  imageToShow: any;
  isImageLoading: boolean;

  constructor(private uploadService: UploadFileService) {}

  ngOnInit() {
    this.isImageLoading = true;
    this.uploadService.getImageFileByid(this.uid).subscribe(
        response => {
            if(response) {
              this.createImageFromBlob(response);
              this.isImageLoading = false;
              this.showFileUpload = false;
            }
          },
          err => {
            this.isImageLoading = false;
              console.log('\nError :::: ' + err);
              for(var property in err) {
                console.log(property + "=" + err[property]);
            }      
          }
    );
  }

  createImageFromBlob(image: Blob) {
    let reader = new FileReader();
    reader.addEventListener("load", () => {
       this.imageToShow = reader.result;
    }, false);
 
    if (image) {
       reader.readAsDataURL(image);
    }
 }

  isShowUpload() {
      return this.showFileUpload;
  }

}