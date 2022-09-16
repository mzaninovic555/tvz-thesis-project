import {Injectable} from "@angular/core";
import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from "@angular/common/http";
import {Observable, tap} from "rxjs";
import {Router} from "@angular/router";

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {

  constructor(private router: Router) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const token = localStorage.getItem('accessToken');

    if (token) {
      request = request.clone({
        setHeaders: {
          Authorization: 'Bearer ' + token
        }
      });
    }

    return next.handle(request).pipe(
        tap(() => {
        }, (err: any) => {
          switch (err.status) {
            case 400:
              if (!request.url.includes('login')) this.router.navigate(['badRequest']);
              break;
            case 403:
              this.router.navigate(['forbidden']);
              break;
            case 404:
              this.router.navigate(['notFound']);
              break;
            case 500:
              this.router.navigate(['internalError']);
              break;
          }
        })
    );
  }
}
