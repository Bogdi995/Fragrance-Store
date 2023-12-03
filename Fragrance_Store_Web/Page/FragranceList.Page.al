page 50107 "Fragrance List"
{
    PageType = List;
    ApplicationArea = All;
    UsageCategory = Administration;
    SourceTable = Fragrance;
    Caption = 'Fragrance List';
    CardPageId = "Fragrance Card";

    layout
    {
        area(Content)
        {
            repeater(Control1)
            {
                field(ID; Rec.ID)
                {
                    ToolTip = 'Specifies the ID of the fragrance.';
                    ApplicationArea = All;
                }
                field(Name; Rec.Name)
                {
                    ToolTip = 'Specifies the name of the fragrance.';
                    ApplicationArea = All;
                }
                field(Brand; Rec.Brand)
                {
                    ToolTip = 'Specifies the brand of the fragrance.';
                    ApplicationArea = All;
                }
                field(Price; Rec.Price)
                {
                    ToolTip = 'Specifies the price of the fragrance.';
                    ApplicationArea = All;
                }
                field(Concentration; Rec.Concentration)
                {
                    ToolTip = 'Specifies the concentration of the fragrance.';
                    ApplicationArea = All;
                }
                field(Quantity; Rec.Quantity)
                {
                    ToolTip = 'Specifies the quantity of the fragrance.';
                    ApplicationArea = All;
                }
            }
        }
    }
}