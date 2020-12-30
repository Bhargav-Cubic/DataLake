
import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/catch';
import { FileResponse } from '../fileresponse/fileresponse';
import { DlFileUploadModel } from './dl-file-upload-model';



@Injectable()
export class DlFileUploadService {

    private headers = new Headers({ 'Content-Type': 'application/json' });

    constructor(private http: Http) { }


    pushFileToDLStorage(file: File): Observable<FileResponse> {
        let formdata: FormData = new FormData();

        formdata.append('file', file);

        return this.http.post('/datalakeui//uploadToHdfs', formdata).map((response: Response) => {
            return  <FileResponse>response.json();
        }).catch((err: any) => Observable.throw(err));

    }

    create(dlFileUploadModel: DlFileUploadModel): Observable<FileResponse> {
        return this.http
            .post("/datalakeui/dlfileuploadform", JSON.stringify(dlFileUploadModel), { headers: this.headers }).map((response: Response) => {
                return <FileResponse>response.json();
            }).catch((err: any) => Observable.throw(err));
    }



    private handleError(error: any): Promise<any> {
        console.error('Error', error);
        return Promise.reject(error.message || error);
    }





}