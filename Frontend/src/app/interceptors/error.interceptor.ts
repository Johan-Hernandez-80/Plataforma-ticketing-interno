import { HttpInterceptorFn } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req).pipe(
    catchError((err: HttpErrorResponse) => {
      const errorMsg =
        err.error?.error ||
        err.error?.detalle ||
        err.error?.mensaje ||
        err.statusText;

      return throwError(() => new Error(errorMsg));
    })
  );
};
