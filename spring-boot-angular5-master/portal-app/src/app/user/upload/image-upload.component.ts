import { Component, OnInit, Input } from '@angular/core';
import { HttpResponse, HttpEventType } from '@angular/common/http';
import { UploadFileService } from '../upload-file.service';
 
@Component({
  selector: 'image-upload',
  templateUrl: './image-upload.component.html',
  styleUrls: ['./image-upload.component.css']
})
export class ImageUploadComponent implements OnInit {
 
  @Input()
  id: number
  selectedFiles: FileList
  currentFileUpload: File
  
  progress: { percentage: number } = { percentage: 0 }
 
  constructor(private uploadService: UploadFileService) { }
 
  ngOnInit() {
  }
 
  selectFile(event) {
    const file = event.target.files.item(0)
 
    if (file.type.match('image.*')) {
      this.selectedFiles = event.target.files;
    } else {
      alert('invalid format!');
    }
  }
 
  upload() {
    this.progress.percentage = 0;
 
    this.currentFileUpload = this.selectedFiles.item(0)
    alert('uploading image'+ this.currentFileUpload);
    this.uploadService.pushFileToStorage(this.currentFileUpload, this.id).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
      }
    })
 
    this.selectedFiles = undefined
  }
}