import {AbstractControl, NG_VALIDATORS, Validator} from "@angular/forms";
import {Directive, Input} from "@angular/core";
import {User} from "../domain/user";

@Directive({
  selector: '[usernameInUse]',
  providers: [{provide: NG_VALIDATORS, useExisting: ExistingUsernameValidator, multi: true}]
})
export class ExistingUsernameValidator implements Validator {

  @Input('usernameInUse') forbiddenParameter!: User[];

  validate(control: AbstractControl): {[key: string]: any} | null {
    const alreadyInUse = this.forbiddenParameter.filter((u: User)  => u.username === control.value);

    return (alreadyInUse.length > 0) ? {'usernameInUse': true} : null;
  }
}
