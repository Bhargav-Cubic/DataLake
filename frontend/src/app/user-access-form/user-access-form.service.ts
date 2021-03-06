import { Injectable } from '@angular/core';
import { Headers, Http,Response, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/catch';
import "rxjs/Rx";


import { UserAccessFormModel } from './user-access-form-model';
import { FileResponse } from '../fileresponse/fileresponse';
import { EnvironmentAccessModel } from "../environment-access/environment-access-model";

@Injectable()
export class UserAccessFormService{

   
    private headers = new Headers({ 'Content-Type': 'application/json' });

    constructor(private http: Http) { }

    create(userAccessFormModel : UserAccessFormModel): Observable<FileResponse> {
        return this.http
          .post("/datalakeui/adduser", JSON.stringify(userAccessFormModel), { headers: this.headers }).map((response: Response) => {
            return <FileResponse>response.json();
          }).catch((err: any) => Observable.throw(err));
      }



    private handleError(error: any): Promise<any> {
        console.error('Error', error); 
        return Promise.reject(error.message || error);
      }

      



}