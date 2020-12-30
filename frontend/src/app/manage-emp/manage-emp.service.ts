import { Injectable } from '@angular/core';
import { Headers, Http, Response, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/catch';
import "rxjs/Rx";

import { EmployeeHierarchyModel } from './manage-emp-model';
import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { IfObservable } from 'rxjs/observable/IfObservable';
import { FileResponse } from '../fileresponse/fileresponse';
//var url = 'http://10.156.203.105:10000/datalakeui';
// var url = '';


@Injectable()
export class EmpHierService {
  private headers = new Headers({ 'Content-Type': 'application/json' });
  constructor(private http: Http) {}

  create(empHierModel: EmployeeHierarchyModel): Observable<FileResponse> {
    return this.http
      .post("/datalakeui/appendToHdfs", JSON.stringify(empHierModel), { headers: this.headers }).map((response: Response) => {
        console.log("response ",response);
        return response.json();
      }).catch((err: any) => Observable.throw(err));
  }

  private handleError(error: any): Promise<any> {
    console.error('Error', error);
    return Promise.reject(error.message || error);
  }
}
