import { ExistingUsernameValidator } from './existing-username-validator.directive';

describe('ExistingValidator', () => {
  it('should create an instance', () => {
    expect(new ExistingUsernameValidator()).toBeTruthy();
  });
});
