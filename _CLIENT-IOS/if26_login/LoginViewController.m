//
//  LoginViewController.m
//  if26_login
//
//  Created by if26 on 05/11/13.
//  Copyright (c) 2013 utt. All rights reserved.
//

#import "LoginViewController.h"

@interface LoginViewController ()

@end

@implementation LoginViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)btn_login_pressed:(id)sender {
    NSString *username = self.login.text;
    NSString *password = self.pass.text;
    [self sendLoginWithUsername:username andPassword:password];
}

- (void) sendLoginWithUsername: (NSString *)username andPassword: (NSString *)password{
    /*if(username != nil && password != nil && [username isEqualToString:@"steven"] && [password isEqualToString:@"test"] ){
        NSLog(@"login:%@",@"OK");
    }
    else{
        NSLog(@"login:%@",@"NO");
    }*/
    NSMutableString *requestUrl = [[NSMutableString alloc] initWithString:@"http://train.sandbox.eutech-ssii.com/messenger/login.php?email="];
    [requestUrl appendString:username];
    [requestUrl appendString:@"&password="];
    [requestUrl appendString:password];
    
    NSURLRequest *request = [NSURLRequest requestWithURL:[NSURL URLWithString:requestUrl]];
    NSOperationQueue *queue = [[NSOperationQueue alloc] init];
    [NSURLConnection sendAsynchronousRequest:request queue:queue completionHandler:^(NSURLResponse *response, NSData *responseData, NSError *error) {
        NSString *responseString = [[NSString alloc] initWithData:responseData encoding:NSISOLatin1StringEncoding];
        NSLog(@"response : %@", responseString);
        NSData *jsonData = [responseString dataUsingEncoding:NSUTF8StringEncoding];
        NSDictionary *dict = [NSJSONSerialization JSONObjectWithData:jsonData options:0 error:NULL];
        NSString *value = [dict valueForKey:@"token"];
        self.token.text = value;
    }];
}
@end
