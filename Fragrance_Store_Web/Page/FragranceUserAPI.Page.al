page 50102 "Fragrance User API"
{
    PageType = API;
    Caption = 'Fragrance User Api';
    APIPublisher = 'blaga';
    APIGroup = 'store';
    APIVersion = 'v1.0';
    EntityName = 'user';
    EntitySetName = 'users';
    SourceTable = "Fragrance User";
    DelayedInsert = true;
    ODataKeyFields = SystemId;

    // http://localhost:3048/BC210/api/blaga/store/v1.0/users

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
                field(name; Rec.Name)
                {
                    Caption = 'Name';
                }
                field(surname; Rec.Surname)
                {
                    Caption = 'Surname';
                }
                field(address; Rec.Address)
                {
                    Caption = 'Address';
                }
                field(dummyString; Rec."Dummy String")
                {
                    Caption = 'Dummy String';
                }
            }
        }
    }
}