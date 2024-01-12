import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { SignUpPageComponent } from './components/sign-up-page/sign-up-page.component';
import { ForgotPasswordPageComponent } from './components/forgot-password-page/forgot-password-page.component';
import { GithubSearchPageComponent } from './components/github-search-page/github-search-page.component';

const routes: Routes = [
  
      {
        path : '',
        component : LoginPageComponent
      },
      {
        path : 'signup',
        component : SignUpPageComponent
      },
      {
        path : 'forgotPassword',
        component : ForgotPasswordPageComponent
      },
      {
        path : 'GithubSearchEngine',
        component :GithubSearchPageComponent
      }
    
    
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
