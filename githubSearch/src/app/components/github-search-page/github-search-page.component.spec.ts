import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GithubSearchPageComponent } from './github-search-page.component';

describe('GithubSearchPageComponent', () => {
  let component: GithubSearchPageComponent;
  let fixture: ComponentFixture<GithubSearchPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GithubSearchPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GithubSearchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
