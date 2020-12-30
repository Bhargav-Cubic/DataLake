import {Injectable} from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/catch';
import {FileResponse} from '../fileresponse/fileresponse';


@Injectable()
export class UploadFileService {

    constructor(private http: Http) { }

    pushFileToStorage(file: File): Observable<FileResponse> {
        let formdata: FormData = new FormData();

        formdata.append('file', file);

        return this.http.post('/datalakeui/addMasterVariant', formdata).map((response: Response) => {
                             return < FileResponse > response.json();
                         }).catch((err: any) => Observable.throw(err));

    }

}