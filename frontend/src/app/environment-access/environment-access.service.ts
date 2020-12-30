import { Injectable } from "@angular/core";
import { Http, Response } from "@angular/http";
import { Observable } from "rxjs/Observable";
import "rxjs/Rx";
import { EnvironmentAccessModel } from "./environment-access-model";



@Injectable()
export class EnvironmentAccessService {


    constructor(private http: Http) {
    }

    private url = "/datalakeui/envaccess";

    getEnvAccess(): Observable<EnvironmentAccessModel[]> {

        console.log("I am under the service");


        return this.http
            .get(this.url)
            .map((response: Response) => {
                return <EnvironmentAccessModel[]>response.json();
            })
            .catch(this.handleError);
    }

    private handleError(error: Response) {
        return Observable.throw(error.statusText);
    }

}


