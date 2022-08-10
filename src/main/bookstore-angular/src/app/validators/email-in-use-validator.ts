import {Directive, Input} from "@angular/core";
import {AbstractControl, NG_VALIDATORS} from "@angular/forms";
import {User} from "../domain/user";

@Directive({
  selector: '[emailInUse]',
  providers: [{provide: NG_VALIDATORS, useExisting: EmailInUseValidator, multi: true}]
})
export class EmailInUseValidator {

  @Input('emailInUse') forbiddenParameter!: User[];

  validate(control: AbstractControl): {[key: string]: any} | null {
    const alreadyInUse = this.forbiddenParameter.filter((u: User)  => u.email === control.value);

    return (alreadyInUse.length > 0) ? {'emailInUse': true} : null;
  }
}
