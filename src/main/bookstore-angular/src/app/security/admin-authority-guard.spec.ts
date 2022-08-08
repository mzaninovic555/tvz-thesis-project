import {AdminAuthorityGuard} from './admin-authority-guard';

describe('AdminAuthorityGuard', () => {
  it('should create an instance', () => {
    expect(new AdminAuthorityGuard()).toBeTruthy();
  });
});
