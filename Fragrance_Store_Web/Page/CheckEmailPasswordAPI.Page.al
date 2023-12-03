page 50109 "Check Email Password API"
{
    PageType = API;
    Caption = 'Check Email Password API';
    APIPublisher = 'blaga';
    APIGroup = 'store';
    APIVersion = 'v1.0';
    EntityName = 'check';
    EntitySetName = 'checks';
    SourceTable = "Check Email Password";
    DelayedInsert = true;
    ModifyAllowed = false;
    DeleteAllowed = false;
    ODataKeyFields = SystemId;

    // http://localhost:3048/BC210/api/blaga/store/v1.0/checks

    layout
    {
        area(Content)
        {
            group(Control1)
            {
                field(systemId; Rec.SystemId)
                {
                    Caption = 'SystemId';
                }
                field(email; Rec.Email)
                {
                    Caption = 'Email';
                }
                field(password; Rec.Password)
                {
                    Caption = 'Password';
                }
                field(check; SingleInstance.GetIsSuccessfullyAuthenticated())
                {
                    Caption = 'Check';
                }
            }
        }
    }

    var
        SingleInstance: Codeunit "Single Instance";
}