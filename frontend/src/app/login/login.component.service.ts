import { Injectable } from '@angular/core';
import { Headers, Http, Response, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/catch';
import "rxjs/Rx";


import { LoginFormModel } from './login.component.model';
import { FileResponse } from '../fileresponse/fileresponse';
import { GroupMenuAccessModel } from './GroupMenuAccessModel';
import { LoginResponse } from './login.component.response';
// var url = 'http://10.156.203.105:10000';
var url = '';



@Injectable()
export class LoginFormService {


    constructor(private http: Http) {
    }

    getUserGroupAccess(username: String, password: String): Observable<LoginResponse[]> {


        return this.http
            // .post("/datalakeui/loginauth/" + username + ":" + password,null)
            .post("/datalakeui/loginauth/" + username + ":" + password,null)
            .map((response: Response) => {
                return <LoginResponse[]>response.json();
            })
            .catch(this.handleError);
    }



/*
    getGroupMenuAccess(username:String): Observable<GroupMenuAccessModel[]> {
        return this.http.get("/datalakeui/groupmenuitems/"+username).map((response: Response) => {
            return <GroupMenuAccessModel[]>response.json();
        }).catch(this.handleError);

    }*/


    getGroupMenuAccess(username:String): Observable<String[]> {
        return this.http.get(url+"/datalakeui/groupmenuitems/"+username).map((response: Response) => {
            return <String[]>response.json();
        }).catch(this.handleError);

    }

    private handleError(error: Response) {
        return Observable.throw(error.statusText);
    }




}