import {AuthenticationGuard} from './authentication-guard.service';

describe('LoggedInGuard', () => {
  it('should create an instance', () => {
    // @ts-ignore
    expect(new AuthenticationGuard()).toBeTruthy();
  });
});
