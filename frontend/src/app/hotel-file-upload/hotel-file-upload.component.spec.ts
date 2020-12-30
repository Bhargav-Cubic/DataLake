import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelFileUploadComponent } from './hotel-file-upload.component';

describe('HotelFileUploadComponent', () => {
  let component: HotelFileUploadComponent;
  let fixture: ComponentFixture<HotelFileUploadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HotelFileUploadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HotelFileUploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
