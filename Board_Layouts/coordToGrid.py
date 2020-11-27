ans = input("enter coordinates. eg 'a16' \n")

# for my numbering
while ans != '':
    row = int(ans[1:])
    col = int( ord(ans[0].lower()) - 96 )
    num = (row * 21) + col
    print(num)
    ans = input("enter coordinates. eg 'a16' \n")
    
#for debord's numbering
# while ans != '':
#     row = int(ans[1:])
#     col = int( 21 - (ord(ans[0].lower()) - 96) )
#     num = (row * 21) + col
#     print(num)
#     ans = input("enter coordinates. eg 'a16' \n")