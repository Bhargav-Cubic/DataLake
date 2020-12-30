import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPccComponent } from './manage-hns.component';

describe('AddPccComponent', () => {
  let component: AddPccComponent;
  let fixture: ComponentFixture<AddPccComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddPccComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddPccComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
