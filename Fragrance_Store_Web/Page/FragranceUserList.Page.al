page 50100 "Fragrance User List"
{
    PageType = List;
    ApplicationArea = All;
    UsageCategory = Administration;
    SourceTable = "Fragrance User";
    Caption = 'Fragrance User List';
    CardPageId = "Fragrance User Card";

    layout
    {
        area(Content)
        {
            repeater(Control1)
            {
                field(Email; Rec.Email)
                {
                    ToolTip = 'Specifies the email of the user';
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
            }
        }
    }
}