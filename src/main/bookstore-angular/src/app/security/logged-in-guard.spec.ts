import { LoggedInGuard } from './logged-in-guard';

describe('LoggedInGuard', () => {
  it('should create an instance', () => {
    expect(new LoggedInGuard()).toBeTruthy();
  });
});
