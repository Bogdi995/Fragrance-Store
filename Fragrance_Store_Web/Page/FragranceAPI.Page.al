page 50108 "Fragrance API"
{
    PageType = API;
    Caption = 'Order Api';
    APIPublisher = 'blaga';
    APIGroup = 'store';
    APIVersion = 'v1.0';
    EntityName = 'fragrance';
    EntitySetName = 'fragrances';
    SourceTable = Fragrance;
    DelayedInsert = true;
    ODataKeyFields = SystemId;

    // http://localhost:3048/BC210/api/blaga/store/v1.0/fragrances

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
                field(id; Rec.ID)
                {
                    Caption = 'ID';
                }
                field(name; Rec.Name)
                {
                    Caption = 'Name';
                }
                field(brand; Rec.Brand)
                {
                    Caption = 'Brand';
                }
                field(image; Rec.Image)
                {
                    Caption = 'Image';
                }
                field(price; Rec.Price)
                {
                    Caption = 'Price';
                }
                field(concentration; Rec.Concentration)
                {
                    Caption = 'Concentration';
                }
                field(quantity; Rec.Quantity)
                {
                    Caption = 'Quantity';
                }
                field(pricePerAmount; Rec."Price per Amount")
                {
                    Caption = 'Price per Amount';
                }
                field(baseNotes; Rec."Base Notes")
                {
                    Caption = 'Base Notes';
                }
                field(midNotes; Rec."Mid Notes")
                {
                    Caption = 'Mid Notes';
                }
                field(topNotes; Rec."Top Notes")
                {
                    Caption = 'Top Notes';
                }
            }
        }
    }
}