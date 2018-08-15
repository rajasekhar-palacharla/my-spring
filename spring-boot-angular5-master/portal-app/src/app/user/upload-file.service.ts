import {Injectable} from '@angular/core';
import {HttpClient, HttpRequest, HttpEvent} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
 
@Injectable()
export class UploadFileService {
 
  private userUrl = 'http://localhost:8080/user-portal/user';

  constructor(private http: HttpClient) {}
 
  pushFileToStorage(file: File, id: number): Observable<HttpEvent<{}>> {
    let formdata: FormData = new FormData();
 
    formdata.append('file', file);
    formdata.set('id', id+'');
 
    // const req = new HttpRequest('POST', this.userUrl + '/imageUpload', formdata, {
    //   reportProgress: true,
    //   responseType: 'text'
    // });
    // this.http.request(req)
    
    return this.http.post<any>(this.userUrl + '/imageUpload', formdata);;
  }
 
  getImageFileByid(id: number): Observable<Blob> {
     return this.http.get(this.userUrl + '/imageByID/' + id, { responseType: 'blob' });
  }
  
}