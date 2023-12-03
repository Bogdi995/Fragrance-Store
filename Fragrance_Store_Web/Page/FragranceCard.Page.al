page 50106 "Fragrance Card"
{
    PageType = Card;
    ApplicationArea = All;
    UsageCategory = Administration;
    SourceTable = Fragrance;
    Caption = 'Fragrance Card';

    layout
    {
        area(Content)
        {
            group(Details)
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
                field(Image; Rec.Image)
                {
                    ToolTip = 'Shows the image of the fragrance';
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
                field("Price per Amount"; Rec."Price per Amount")
                {
                    ToolTip = 'Specifies the price of the fragrance per 100 ml.';
                    ApplicationArea = All;
                }
                field("Base Notes"; Rec."Base Notes")
                {
                    ToolTip = 'Specifies the base notes of the fragrance.';
                    ApplicationArea = All;
                }
                field("Mid Notes"; Rec."Mid Notes")
                {
                    ToolTip = 'Specifies the mid notes of the fragrance.';
                    ApplicationArea = All;
                }
                field("Top Notes"; Rec."Top Notes")
                {
                    ToolTip = 'Specifies the top notes of the fragrance.';
                    ApplicationArea = All;
                }
            }
        }
    }
}