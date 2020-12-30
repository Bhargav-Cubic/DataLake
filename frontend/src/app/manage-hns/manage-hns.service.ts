import { Injectable } from '@angular/core';
import { Headers, Http, Response, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/catch';
import "rxjs/Rx";

import { MatchHNSModel } from './manage-hns-model';
import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { IfObservable } from 'rxjs/observable/IfObservable';
import { FileResponse } from '../fileresponse/fileresponse';
// var url = 'http://10.156.203.105:10000';
var url = '';



@Injectable()
export class UploadHNSService {
  private headers = new Headers({ 'Content-Type': 'application/json' });
  constructor(private http: Http) {}

  // create(matchHNSModel: MatchHNSModel): Observable<FileResponse> {
  //   return this.http
  //     .post("/datalakeui/addpcc", JSON.stringify(matchHNSModel), { headers: this.headers }).map((response: Response) => {
  //       return <FileResponse>response.json();
  //     }).catch((err: any) => Observable.throw(err));
  // }

  private handleError(error: any): Promise<any> {
    console.error('Error', error);
    return Promise.reject(error.message || error);
  }

  pushFileToStorage(file: File): Observable<FileResponse> {
    console.log("Calling here to UploadHNSService pushFileToStorage");
    let formdata: FormData = new FormData();

    formdata.append('file', file);

    return this.http.post(url+'/datalakeui/addHNSMatch', formdata).map((response: Response) => {
                         return < FileResponse > response.json();
                     }).catch((err: any) => Observable.throw(err));

  }
  
}
