import {ComponentFixture, TestBed} from '@angular/core/testing';

import {FooterBookstoreComponent} from './footer-bookstore.component';

describe('FooterBookstoreComponent', () => {
  let component: FooterBookstoreComponent;
  let fixture: ComponentFixture<FooterBookstoreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FooterBookstoreComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FooterBookstoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
