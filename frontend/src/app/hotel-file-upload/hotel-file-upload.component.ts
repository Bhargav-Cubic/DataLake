import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpResponse, HttpEventType } from '@angular/common/http';
import { UploadFileService } from './hotel-file-upload.service';
import { ViewChild } from '@angular/core';
import { HttpModule } from '@angular/http';

@Component({
  selector: 'app-hotel-file-upload',
  templateUrl: './hotel-file-upload.component.html',
  styleUrls: ['./hotel-file-upload.component.scss']
})
export class HotelFileUploadComponent implements OnInit {
	
	selectedFiles: FileList
    currentFileUpload: File
    status: boolean = true;
    visible: boolean = false;
    btnDisable: boolean = true;
    responseLabel: string = ""
	@ViewChild('fileUpload')
    fileupload: any;
	
   constructor(private uploadService: UploadFileService) { 
    
   }
	

  ngOnInit() {
   
  }
  selectFile(event) {
        this.selectedFiles = event.target.files;
		console.log("printing the file names");
		console.log(event.target.value);
		this.btnDisable = false;
        this.responseLabel = "";
    }
	 upload() {

        this.currentFileUpload = this.selectedFiles.item(0)
        this.uploadService.pushFileToStorage(this.currentFileUpload).subscribe(event => {
            console.log('in response event')
            console.log(event);
            this.visible = true;
            this.status = event.status;
            var message = event.message;
            var input = message.split(",");
            console.log('input.length');
            console.log(input.length);
            var i = 0;
            var str = "";
            while (i < input.length) {
                console.log(input[i]);
                str += "\n"+input[i];
                i++;
                
            }
            console.log('string value');
            console.log(str);

            this.responseLabel = str;
            this.btnDisable = true;
            this.fileupload.nativeElement.value = "";
        },
            (err) => {
                console.log('in error');
                this.btnDisable = true;
                this.fileupload.nativeElement.value = "";
            });



    }

}
