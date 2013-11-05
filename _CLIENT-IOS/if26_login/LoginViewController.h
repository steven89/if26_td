//
//  LoginViewController.h
//  if26_login
//
//  Created by if26 on 05/11/13.
//  Copyright (c) 2013 utt. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface LoginViewController : UIViewController

@property (weak, nonatomic) IBOutlet UITextField *login;
@property (weak, nonatomic) IBOutlet UITextField *pass;
@property (weak, nonatomic) IBOutlet UILabel *token;
@property (weak, nonatomic) IBOutlet UIButton *btn_login;


- (IBAction)btn_login_pressed:(id)sender;
- (void) sendLoginWithUsername: (NSString *)username andPassword: (NSString *)password;

@end
