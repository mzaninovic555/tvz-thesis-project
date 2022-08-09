import {Component, ElementRef} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'bookstore-angular';

  constructor(private elementRef: ElementRef) { }


  ngAfterViewInit() {
    let s = document.createElement("script");
    s.type = "text/javascript";
    s.src = "https://kit.fontawesome.com/a5aa6144c0.js";
    this.elementRef.nativeElement.appendChild(s);

    s = document.createElement("script");
    s.type = "text/javascript";
    s.src = "js/bootstrap-multiselect/bootstrap-multiselect.js";
    this.elementRef.nativeElement.appendChild(s);
  }
}
