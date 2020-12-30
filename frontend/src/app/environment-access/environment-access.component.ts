import { Component, OnInit } from '@angular/core';
import { EnvironmentAccessModel } from './environment-access-model';
import { EnvironmentAccessService } from './environment-access.service';
import { PipeTransform, Pipe } from '@angular/core';
import { forEach } from '@angular/router/src/utils/collection';


@Component({
  selector: 'app-environment-access',
  templateUrl: './environment-access.component.html',
  styleUrls: ['./environment-access.component.scss']
})



export class EnvironmentAccessComponent implements OnInit {


  dev_select:number=0;
  prod_select:number=0;
  sbox_select:number=0;
  dropdownList_dev = [];
  dropdownList_prod = [];
  dropdownList_sbox = [];
  selectedItems = [];
  dropdownSettings_dev = {};
  dropdownSettings_prod = {};
  dropdownSettings_sbox = {};
  environmentAccessModel: EnvironmentAccessModel[];

  constructor(private environmentAccessService: EnvironmentAccessService) {

  }



  ngOnInit() {
    this.getenvAccess(); 
    
    this.dropdownSettings_dev = {
      singleSelection: false,
      text: "Select Development schemas",
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      enableSearchFilter: true,
      classes: "myclass custom-class"
    };
    this.dropdownSettings_prod = {
      singleSelection: false,
      text: "Select prod schemas",
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      enableSearchFilter: true,
      classes: "myclass custom-class"
    };
    this.dropdownSettings_sbox = {
      singleSelection: false,
      text: "Select sandbox schemas",
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      enableSearchFilter: true,
      classes: "myclass custom-class"
    };
  }

  onChange_dev($event){
     this.dropdownList_dev=[]
    for (let value of this.environmentAccessModel) {
      if (value.environmentName == "development") {
        var obj = { "id": value.sequence_id, "itemName": value.schemaName }
        this.dropdownList_dev.push(obj);
      }
    }

  }
  onChange_prod($event){
    this.dropdownList_prod=[]
    for (let value of this.environmentAccessModel) {
      if (value.environmentName == "production") {
        var obj = { "id": value.sequence_id, "itemName": value.schemaName }
        this.dropdownList_prod.push(obj);
      }
    }

 }
 onChange_sbox($event){
  this.dropdownList_sbox=[]
  for (let value of this.environmentAccessModel) {
    if (value.environmentName == "sandbox") {
      var obj = { "id": value.sequence_id, "itemName": value.schemaName }
      this.dropdownList_sbox.push(obj);
    }
  }

}


  
  getenvAccess(): void {
    this.environmentAccessService.getEnvAccess().subscribe(resultArray => {
      this.environmentAccessModel = resultArray
    },
      error => console.log("Error :: " + error));
  }
  onItemSelect_dev(item: any) {
    console.log(item);
    this.dev_select++;
    console.log(this.dev_select)

  }
  OnItemDeSelect_dev(item: any) {
    console.log(item);
    this.dev_select--;
    console.log(this.dev_select)
  }
  onSelectAll_dev(items: any) {
    console.log(items);
    this.dev_select++;
  }
  onDeSelectAll_dev(items: any) {
    console.log(items);
    this.dev_select=0
  }
  onItemSelect_prod(item: any) {
    console.log(item);
    this.prod_select++;
    console.log(this.prod_select)

  }
  OnItemDeSelect_prod(item: any) {
    console.log(item);
    this.prod_select--;
    console.log(this.prod_select)

  }
  onSelectAll_prod(items: any) {
    console.log(items);
  }
  onDeSelectAll_prod(items: any) {
    console.log(items);
  }
  onItemSelect_sbox(item: any) {
    console.log(item);
    this.sbox_select++;
    console.log(this.sbox_select)

  }
  OnItemDeSelect_sbox(item: any) {
    console.log(item);
    this.sbox_select--;
    console.log(this.sbox_select)

  }
  onSelectAll_sbox(items: any) {
    console.log(items);
  }
  onDeSelectAll_sbox(items: any) {
    console.log(items);
  }

  validate_env($event){
    if(this.dev_select==0 && this.prod_select==0 && this.sbox_select==0){
      console.log("in valid")
    }else{
    console.log("valid");
        
    }
  }





}
