codeunit 50101 "Single Instance"
{
    SingleInstance = true;

    var
        SuccessfullyAuthenticated: Boolean;

    procedure SetSuccessfullyAuthenticated(IsSuccessfullyAuthenticated: Boolean)
    begin
        SuccessfullyAuthenticated := IsSuccessfullyAuthenticated;
    end;

    procedure GetIsSuccessfullyAuthenticated(): Boolean
    begin
        exit(SuccessfullyAuthenticated);
    end;
}