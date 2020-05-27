public class SupplySearchResultActivity extends BaseActivity implements View.OnClickListener, ISupplySearchResultView, OnProductIDClickListener {

    private View ll_type;
    private View ll_synthesize;
    private View ll_filter;
    private View ll_content;
    private View rl_prompt;
    private RelativeLayout ll_filter_out;
    private TextView tv_synthesize;
    private ImageView iv_arrow_synthesize;//综合
    private TextView tv_type;
    private TextView tv_filter;     //筛选
    private ImageView iv_filter;    //筛选图片
    private EditText et_search_supplies;
    private ImageView iv_delete;
    private ImageView iv_top_left;
    private ImageView iv_arrow_type;
    private RecyclerView rv_search_result;
    private SupplySearchAdapter adapter;
    private SmartRefreshLayout srl_fresh;
    private CommonForm form;
    private SupplySearchResultPresenter presenter;
    private List<SupplyListB.SaleListBean> list = new ArrayList<>();
    private SynthesisPopWindow synthesisPopWindow;                  //综合
    private ProductScreeningPopWindow productScreeningPopWindow;    //产品分类
    private boolean isProductClass = true;                          //true 产品分类 false 不是产品分类
    private BaseSPManager baseSPManager;


    @Override
    public SupplySearchResultPresenter getPresenter() {
        if (BaseUtils.isEmptyObj(presenter)) {
            presenter = new SupplySearchResultPresenter(this);
        }
        return presenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_supply_search_result;
    }

    @Override
    protected void initView() {
        iv_delete = findViewById(R.id.iv_delete);
        iv_top_left = findViewById(R.id.iv_top_left);
        iv_arrow_synthesize = findViewById(R.id.iv_arrow_synthesize);
        iv_arrow_type = findViewById(R.id.iv_arrow_type);
        iv_filter = findViewById(R.id.iv_filter);
        tv_filter = findViewById(R.id.tv_filter);
        ll_type = findViewById(R.id.ll_type);
        ll_synthesize = findViewById(R.id.ll_synthesize);
        ll_filter = findViewById(R.id.ll_filter);
        ll_filter_out = findViewById(R.id.ll_filter_out);
        rv_search_result = findViewById(R.id.xrv_search_result);
        et_search_supplies = findViewById(R.id.et_search_supplies);
        tv_synthesize = findViewById(R.id.tv_synthesize);
        tv_type = findViewById(R.id.tv_type);
        srl_fresh = findViewById(R.id.srl_fresh);
        ll_content = findViewById(R.id.ll_content);
        rl_prompt = findViewById(R.id.rl_prompt);
    }

    @Override
    protected void initFormData() {
        form = getParam();
        if (BaseUtils.isEmptyObj(form) && BaseUtils.isEmptyStr(form.getData()))
            finish();
    }

    @Override
    protected void getData() {
        super.getData();
        et_search_supplies.setText(form.getData());
        getPresenter().setContent(form.getData());
        srl_fresh.autoRefresh();
        baseSPManager = BaseSPManager.getInstance();
    }

    @Override
    protected void initListener() {
        hideView(iv_delete);
        ll_type.setOnClickListener(this);
        ll_synthesize.setOnClickListener(this);
        ll_filter.setOnClickListener(this);
        iv_top_left.setOnClickListener(this);
        srl_fresh.setRefreshHeader(new ClassicsHeader(this));
        srl_fresh.setRefreshFooter(new ClassicsFooter(this));
        rv_search_result.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SupplySearchAdapter(this);

        rv_search_result.setAdapter(adapter);
        srl_fresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getPresenter().setNextPage();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getPresenter().setFirstPage();
            }
        });
        purchaseSearchContent();
    }

    private void purchaseSearchContent() {
        et_search_supplies.setOnEditorActionListener((v, actionId, event) -> {
            if ((event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode()
                    && KeyEvent.ACTION_DOWN == event.getAction())) {
                if (BaseUtils.isEmptyStr(BaseUtils.replaceBlank(BaseUtils.getEditText(et_search_supplies)))) {
                    showToast(getString(R.string.txt_input_content));
                    return false;
                } else {
                    addHistoryList(BaseUtils.replaceBlank(BaseUtils.getEditText(et_search_supplies)));
                    getPresenter().setContent(BaseUtils.replaceBlank(BaseUtils.getEditText(et_search_supplies)));
                    srl_fresh.autoRefresh();
                }
            }
            return false;
        });
    }

    /**
     * @description 保存历史记录
     */
    public void addHistoryList(String string) {
        List<String> str = baseSPManager.getStringDataList("supplyHistory");
        if (BaseUtils.isEmptyList(str)) {
            str = new ArrayList<>();
            str.add(string);
        } else {
            if (str.size() > 0) {
                for (int i = 0; i < str.size(); i++) {
                    if (str.get(i).equals(string)) {
                        str.remove(i);
                    }
                }
            }
            if (str.size() < 10) {
                str.add(string);
            } else {
                str.remove(0);
                str.add(string);
            }
        }
        baseSPManager.setDataList("supplyHistory", str);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ll_synthesize) {
            synthesis();
        } else if (id == R.id.ll_filter) {
            isProductClass = false;
            classification();
            productScreeningPopWindow.showAsDropDown(ll_filter_out);
        } else if (id == R.id.iv_top_left) {
            onBackPressed();
        } else if (id == R.id.ll_type) {
            isProductClass = true;
            clickGoodsSelect();
        }
    }

    /**
     * 调用筛选的参数
     */
    private CommonForm getFormData(int type) {
        CommonForm form = new CommonForm();
        if (type == AppStaticRes.TYPE_BULK_RAW_MATERIALS) {
            form.setType(AppStaticRes.SUPPLY_BULKRAW_MATERIALS);
        } else if (type == AppStaticRes.TYPE_ALUMINUM_PROFILES) {
            form.setType(AppStaticRes.SUPPLY_ALUMINUM_PROFILES);
        } else {
            form.setType(AppStaticRes.SUPPLY_SPARE_PARTS);
        }
        return form;
    }

    private void clickGoodsSelect() {
        if (BaseUtils.isEmptyObj(productScreeningPopWindow)) {
            classification();
        }
        productScreeningPopWindow.showAsDropDown(ll_filter_out);
    }

    /**
     * 综合
     */
    private void synthesis() {
        if (BaseUtils.isEmptyObj(synthesisPopWindow)) {
            synthesisPopWindow = new SynthesisPopWindow(this, new SynthesisPopWindow.OnClickSynthesisListener() {
                @Override
                public void onClick(int position, String theName) {
                    getPresenter().setOrderBy(position);
                    tv_synthesize.setText(theName);
                    tv_synthesize.setSelected(true);
                    iv_arrow_synthesize.setImageResource(R.drawable.icon_arrow_press_down);
                    synthesisPopWindow.dismiss();
                    srl_fresh.autoRefresh();
                }

                @Override
                public void onShow() {
                    synthesisPopWindow.isShowing();
                }

                @Override
                public void onDismiss() {

                }
            }, true);
        }
        synthesisPopWindow.showAsDropDown(ll_filter_out);
    }

    /**
     * @description 分类弹窗
     */
    private void classification() {
        productScreeningPopWindow = new ProductScreeningPopWindow(this, -1
                , new ProductScreeningPopWindow.OnClickListener() {

            @Override
            public void onShow() {
                if (isProductClass) {
                    tv_type.setTextColor(Color.parseColor("#1692FF"));
                    iv_arrow_type.setSelected(true);
                }
            }

            @Override
            public void onCLickSuccess(ProductCategoryP.ListBean listBean, ThirdListBean thirdListBean) {
                if (isProductClass) {
                    tv_type.setText(thirdListBean.getThirdCategoryName());
                    iv_arrow_type.setSelected(true);
                    getPresenter().setCategoryId(String.valueOf(thirdListBean.getThirdCategoryId()));
                    srl_fresh.autoRefresh();
                } else {
                    onDismiss();
                    //TODO 筛选
                    CommonForm form = getFormData(listBean.getFirstCategoryId());
                    goToForResult(RightDialogActivity.class, form, AppStaticRes.FILTER_DATA_CODE);
                }
            }

            @Override
            public void onClickReset() {
                if (isProductClass) {
                    getPresenter().setCategoryId("-1");
                    tv_type.setText("分类");
                    tv_type.setTextColor(Color.parseColor("#061E3A"));
                    srl_fresh.autoRefresh();
                }
            }

            @Override
            public void onDismiss() {
                if (isProductClass) {
                    tv_type.setTextColor(Color.parseColor("#061E3A"));
                    iv_arrow_type.setSelected(false);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppStaticRes.FILTER_DATA_CODE && resultCode == AppStaticRes.FILTER_DATA_CODE && !BaseUtils.isEmptyObj(data)) {
            String string = data.getStringExtra(AppStaticRes.FILTER_DATA);
            SelectDataB selectDataB = BaseUtils.getGsonObject(string, SelectDataB.class);
            if (selectDataB.getRegion() != 0
                    || selectDataB.getBrand() != 0
                    || selectDataB.getProvince() != 0
                    || selectDataB.getCity() != 0
                    || !BaseUtils.isEmptyStr(selectDataB.getInputFormat())) {

                tv_filter.setTextColor(Color.parseColor("#1692FF"));
                iv_filter.setImageResource(R.drawable.icon_filter_blue);
                getPresenter().setSelectData(selectDataB);
            } else {
                tv_filter.setTextColor(Color.parseColor("#ff061e3a"));
                iv_filter.setImageResource(R.drawable.icon_filter);
            }
        }
    }

    @Override
    public void requestDataFinish() {
        super.requestDataFinish();
        if (rv_search_result != null) {
            srl_fresh.finishRefresh();
            srl_fresh.finishLoadMore();
        }
    }

    /**
     * 搜索列表
     */
    @Override
    public void onSupplyListSuccess(SupplyListB purchaseAndSaleB) {
        if (BaseUtils.activityIsDestory(this) || BaseUtils.isEmptyObj(purchaseAndSaleB))
            return;

        if (BaseUtils.isEmptyList(purchaseAndSaleB.getSaleList()) && purchaseAndSaleB.getPageNum() == 1) {
            adapter.clearData();
            hideView(ll_content);
            showView(rl_prompt);
        } else {
            showView(ll_content);
            hideView(rl_prompt);
            adapter.addData(purchaseAndSaleB.getSaleList());
        }
    }

    @Override
    public void onItemClickListener(long productId) {
        ControllerFactory.getAppController()
                .gotoPurchaserOrderDetails(String.valueOf(productId), false, -1);
    }
}