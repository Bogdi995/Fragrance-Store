page 50101 "Fragrance User Card"
{
    PageType = Card;
    ApplicationArea = All;
    UsageCategory = Administration;
    SourceTable = "Fragrance User";
    Caption = 'Fragrance User Card';

    layout
    {
        area(Content)
        {
            group(Details)
            {
                Caption = 'Details';
                field(Email; Rec.Email)
                {
                    ToolTip = 'Spicifies the email of the user';
                    ApplicationArea = All;
                }
                field(Name; Rec.Name)
                {
                    ToolTip = 'Specifies the name of the user';
                    ApplicationArea = All;
                }
                field(Surname; Rec.Surname)
                {
                    ToolTip = 'Specifies the surname of the user';
                    ApplicationArea = All;
                }
                field(Address; Rec.Address)
                {
                    ToolTip = 'Spicifies the address of the user';
                    ApplicationArea = All;
                }
                field(Password; Rec.Password)
                {
                    ToolTip = 'Specifies the password of the user';
                    ApplicationArea = All;
                }
            }
        }
    }
}